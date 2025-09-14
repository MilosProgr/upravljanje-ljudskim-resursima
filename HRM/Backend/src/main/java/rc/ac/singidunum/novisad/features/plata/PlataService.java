package rc.ac.singidunum.novisad.features.plata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.korisnikHandler.KorisnikPravoPristupa;
import rc.ac.singidunum.novisad.features.korisnikHandler.KorisnikPravoPristupaRepository;
import rc.ac.singidunum.novisad.features.plata.PlataDTO.PlataDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.Zaposleni;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniService;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;
import rc.ac.singidunum.novisad.generics.service.GenericCrudService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class PlataService extends GenericCrudService<PlataDTORecord, Plata, Long> {

    @Autowired
    private ZaposleniService zaposleniService;

//    @Autowired
//    private ZaposleniMapper zaposleniMapper;

    @Autowired
    private PlataRepository plataRepository;

    @Autowired
    private PlataMapper plataMapper;

    @Autowired
    private KorisnikPravoPristupaRepository korisnikPravoPristupaRepository;

    protected PlataService(JpaRepository<Plata, Long> repository, Mapper<PlataDTORecord, Plata> mapper) {
        super(repository, mapper);
    }

    @Scheduled(cron = "0 0 0 1 * ?") // Runs at midnight on the 1st of every month
    public void generisiPlateZaNoviMesec() {
        LocalDate danas = LocalDate.now();
        Plata plata = new Plata();
        plata.setDatumIsplate(danas);
        plataRepository.save(plata);

        System.out.println("Plate su automatski generisane za datum: " + danas);
    }

    public PlataDTORecord dobaviPlatuPrekoPravaPristupa(Long zaposlenId) {
        // 1. Nađi zaposlenog
        Zaposleni zaposleni = zaposleniService.findZaposlenById(zaposlenId);
        if (zaposleni == null || zaposleni.getDatumZaposlenja() == null) {
            return null; // ili baci exception po potrebi
        }

        LocalDate datumIsplate = izracunajSledeciDatumIsplate(zaposleni.getDatumZaposlenja());

        // 2. Pokušaj da pronađeš platu iz prava pristupa
        Optional<Plata> plataOpt = nadjiPlatuZaZaposlenog(zaposlenId);

        // 3. Ako plata postoji → vrati je sa mapper-om
        if (plataOpt.isPresent()) {
            Plata plata = plataOpt.get();
            plata.setDatumIsplate(datumIsplate);
            return plataMapper.map(plata);
        }

        // 4. Ako nema → vrati DTO sa default vrednostima
        return new PlataDTORecord(
                null,                 // id plate
                datumIsplate,         // izračunat datum isplate
                BigDecimal.ZERO       // podrazumevani iznos
        );
    }

    // Pomoćna metoda: pronalazi platu iz prava pristupa
    private Optional<Plata> nadjiPlatuZaZaposlenog(Long zaposlenId) {
        List<KorisnikPravoPristupa> prava = korisnikPravoPristupaRepository.findByZaposlenId(zaposlenId);
        return prava.stream()
                .map(KorisnikPravoPristupa::getPozicija)
                .filter(p -> p != null && p.getPlata() != null)
                .map(p -> p.getPlata())
                .findFirst();
    }

    // Pomoćna metoda: računa sledeći datum isplate na osnovu datuma zaposlenja
    private LocalDate izracunajSledeciDatumIsplate(LocalDate datumZaposlenja) {
        LocalDate danas = LocalDate.now();
        YearMonth startMonth = YearMonth.from(datumZaposlenja);
        YearMonth candidateMonth = startMonth;

        while (!candidateMonth.atDay(datumZaposlenja.getDayOfMonth()).isAfter(danas) &&
               !candidateMonth.atDay(datumZaposlenja.getDayOfMonth()).isEqual(danas)) {
            candidateMonth = candidateMonth.plusMonths(1);
        }

        try {
            return candidateMonth.atDay(datumZaposlenja.getDayOfMonth());
        } catch (Exception e) {
            return candidateMonth.atEndOfMonth();
        }
    }
}
