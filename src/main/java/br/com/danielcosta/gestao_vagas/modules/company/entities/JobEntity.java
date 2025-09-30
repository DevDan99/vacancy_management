package br.com.danielcosta.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

// Classe de entidade que representa uma vaga de emprego no sistema de gestão de vagas.
@Entity(name = "job") // Anotação JPA para mapear esta classe para a tabela "job" no banco de dados.
@Data
public class JobEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID) // Geração automática de UUID para o campo id.
	private UUID id;
	private String description;
	private String benefits;
	private String level;

	@ManyToOne() // Relacionamento muitos-para-um com a entidade CompanyEntity.
	@JoinColumn(name = "company_id", insertable = false, updatable = false) // Especifica que o relacionamento será feito através da coluna company_id na tabela job.
	private CompanyEntity companyEntity; // Este campo representa o objeto empresa relacionado à vaga.

	@Column(name = "company_id") // Mapeia o campo companyId para a coluna "company_id" na tabela "job".
	private UUID companyId;

	@CreationTimestamp // Anotação Hibernate para definir automaticamente o timestamp de criação.
	private LocalDateTime createdAt;
}
