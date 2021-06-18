package es.iesrafaelalberti.daw.dwes.proyecto.services;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.LocalidadRepository;
import es.iesrafaelalberti.daw.dwes.proyecto.repositories.TrabajadorRepository;
import es.iesrafaelalberti.daw.dwes.proyecto.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class ImageService {


    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public boolean imageStore(MultipartFile file, Class entity, Long id) throws IOException {
        try {
            Object myObject = entityManager.find(entity, id);

            String myFileName = entity.getSimpleName() + "_" + id.toString() + "_" + file.getOriginalFilename();

            Path targetPath = Paths.get("./images/" +
                    myFileName)
                    .normalize();
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            Method method = entity.getMethod("setImageUrl", String.class);
            method.invoke(myObject, "/download/" + myFileName);
            entityManager.persist(entity.cast(myObject));
        } catch (Exception e) {
            throw new EntityNotFoundException(entity.getSimpleName() + ": " + id.toString());
        }

        return true;
    }


}