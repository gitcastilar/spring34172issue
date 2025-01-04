package spring.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EdadController {
    private final ServiceDao dao;
	@GetMapping("/edad")
	public ResponseEntity<EdadEntModel> getFecNac(@RequestParam String fechaNac){
		return ResponseEntity.ok(dao.getFecnac(fechaNac));			
	}
}
