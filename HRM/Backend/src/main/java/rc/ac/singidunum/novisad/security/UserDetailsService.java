package rc.ac.singidunum.novisad.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.korisnikHandler.KorisnikPravoPristupa;
import rc.ac.singidunum.novisad.features.zaposlen.Zaposleni;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniService;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	ZaposleniService zaposleniService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Zaposleni> zaposlen = zaposleniService.findKorisnikByKorisnickoIme(username);
		if(zaposlen.isPresent()) {
			ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			
			for(KorisnikPravoPristupa pravoPristupa : zaposlen.get().getPravoPristupa()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(pravoPristupa.getPozicija().getNaziv()));
			}
			
			return new org.springframework.security.core.userdetails.User(zaposlen.get().getKorisnickoIme(), zaposlen.get().getLozinka(), grantedAuthorities);
		}
		return null;
	}

}
