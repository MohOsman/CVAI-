package se.mohosman.cvai.api;

import groovy.transform.AutoImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.mohosman.cvai.service.CVService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/cv")
public class CVController {

    private final Path rootLocation = Paths.get("src/uploads");
    @Autowired
    private CVService cvService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Failed to store empty file.");
            }
            if (!Objects.equals(file.getContentType(), "application/pdf")) {
                return ResponseEntity.badRequest().body("Only PDF files are allowed!");
            }
            Files.copy(file.getInputStream(), this.rootLocation.
                    resolve(Objects.requireNonNull(file.getOriginalFilename())));
            return ResponseEntity.ok("File successfully uploaded: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Failed to upload file: " + e.getMessage());
        }
    }

   @GetMapping("/joke")
    public String   joke(){
       return cvService.testChatclient();

    }
}
