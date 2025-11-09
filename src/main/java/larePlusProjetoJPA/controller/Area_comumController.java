package larePlusProjetoJPA.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import larePlusProjetoJPA.entity.Area_comum;
import larePlusProjetoJPA.repository.Area_comumRepository;

@CrossOrigin(origins = "*")
@RestController
	public class Area_comumController {
		@Autowired
		private Area_comumRepository area_comumRepository;
		
		@PostMapping("/area_comum")
		public String saveArea_comum(@RequestBody Area_comum area_comum) {
			area_comumRepository.save(area_comum);
			return "Área cadastrada com sucesso!";
		}
		
		@PostMapping("/area_comum/lista")
		public String saveAreas_comuns(@RequestBody List<Area_comum> areas_comuns) {
			area_comumRepository.saveAll(areas_comuns);
			return "Áreas cadastradas com sucesso!";
		}
		
		@GetMapping("/area_comum")
		public List<Area_comum> getArea_comum() {
			return area_comumRepository.findAll();
		}
		
		@GetMapping("/area_comum/{id}")
		public Area_comum getArea_comumById(@PathVariable Long id) {
			return area_comumRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível localizar a área!"));
		}
		
		@PutMapping("/area_comum/{id}")
		public String updateaArea_comum(@PathVariable Long id, @RequestBody Area_comum novaArea_comum) {
			Area_comum area_comumSalva = area_comumRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Não foi possível localizar a área!"));
			
			area_comumSalva.setNome(novaArea_comum.getNome());
			
			area_comumRepository.save(area_comumSalva);
			return "A área foi atualizada!";
		}
		
		@DeleteMapping("/area_comum/{id}")
		public String deleteArea_comum(@PathVariable Long id) {
			area_comumRepository.deleteById(id);
			return "Área deletada!";
		}

	}

