package CounterStriker.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public  abstract class BaseRepository<G> implements Repository<BaseRepository<G>> {

    private Collection<BaseRepository<G>> baseRepositories = new ArrayList<>();


    @Override
    public Collection<BaseRepository<G>> getModels() {
        return Collections.unmodifiableCollection(baseRepositories);
    }

    @Override
    public void add(BaseRepository<G> model) {
       if(model == null){
           throw new NullPointerException(String.format("Cannot add null in %s Repository",
                   BaseRepository.class.getSimpleName()));
       }
       baseRepositories.add(model);
    }

    @Override
    public boolean remove(BaseRepository<G> model) {
        return baseRepositories.remove(model);
    }

}
