package br.com.danielcosta.gestao_vagas.modules.company.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielcosta.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.danielcosta.gestao_vagas.modules.company.entities.JobEntity;
import br.com.danielcosta.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
	private CreateJobUseCase createJobUseCase;

	@PostMapping("/")
	public JobEntity create(@Valid @RequestBody CreateJobDTO jobDTO, HttpServletRequest request) {
		var companyId = request.getAttribute("companyId"); // Pega o ID da empresa do atributo da requisição

		var jobEntity = JobEntity.builder() // Constrói a entidade JobEntity usando o padrão Builder
				.description(jobDTO.getDescription())
				.benefits(jobDTO.getBenefits())
				.level(jobDTO.getLevel())
				.companyId(UUID.fromString(companyId.toString())) // Define o ID da empresa na entidade
				.build();

		return this.createJobUseCase.execute(jobEntity);
	}
}
