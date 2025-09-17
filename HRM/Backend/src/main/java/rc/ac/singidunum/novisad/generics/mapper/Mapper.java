package rc.ac.singidunum.novisad.generics.mapper;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;




public interface Mapper<T,E> {
	
	T map(E e);
	
    default List<T> map(List<E> e){
    	List<T> output = new ArrayList<T>();
    	
    	if(e != null) {
    		//tradicionalni pristup
    		for(E entity: e) {
    			T mapiranEntitet = map(entity);
    			output.add(mapiranEntitet);
    		}
    		//trenutni pristup
    		//output = e.stream().map(this::map).collect(Collectors.toList());
    	}
    	return output;
    }
    
 
    
    
}
