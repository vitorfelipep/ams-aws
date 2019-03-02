package com.project.ams.funerary.service;

import com.project.ams.funerary.domain.Cliente;
import com.project.ams.funerary.domain.Dependente;
import com.project.ams.funerary.repository.ClienteRepository;
import com.project.ams.funerary.repository.DependenteRepository;
import com.project.ams.funerary.service.exception.ExistingClientWithSameCPFException;
import com.project.ams.funerary.service.exception.ExistingDependentWithSameCPFException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author vitor
 *
 */

@Service
@Transactional
public class ClienteService implements Serializable{

	private static final long serialVersionUID = 6026938304657136768L;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private DependenteRepository dependenteRepository;
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> findOne(Long codigo) {
		return clienteRepository.findById(codigo);
	}

	public Cliente save(@Valid Cliente cliente) {
		clienteAlreadyExistent(cliente);
		
		Cliente clienteSaved = clienteRepository.save(cliente);
		if (!CollectionUtils.isEmpty(clienteSaved.getDependentes())) {
			verififyDependentsBeforeSave(clienteSaved);
			clienteSaved = clienteRepository.save(clienteSaved);
		}
		
		return clienteSaved;
	}


	/**
	 * @param cliente
	 * @throws ExistingClientWithSameCPFException 
	 */
	private void clienteAlreadyExistent(Cliente cliente) {
		Optional<Cliente> clienteOpcional = clienteRepository.findByPessoaCpfIgnoreCase(cliente.getPessoa().getCpf());
		if(clienteOpcional.isPresent()) {
			throw new ExistingClientWithSameCPFException("Já existe um cliente cadastrado com este cpf. method: clienteAlreadyExistent chamado pela clienteService");
		}
	}

	public Cliente update(Long codigo, @Valid Cliente cliente) {
		Optional<Cliente> clienteSaved = clienteRepository.findById(codigo);
		if (clienteSaved.isPresent()) {
			if (!CollectionUtils.isEmpty(cliente.getDependentes())) {
				verififyDependents(cliente, clienteSaved);
			}
			BeanUtils.copyProperties(cliente, clienteSaved.get(), "id");
			return clienteRepository.save(clienteSaved.get());
		} else {
			return clienteSaved.orElse(null);
		}
	}

	/**
	 * @param cliente
	 * @param clienteSaved
	 */
	private void verififyDependents(Cliente cliente, Optional<Cliente> clienteSaved) {
		cliente.getDependentes().forEach(d -> {
			if (d.getId() == null) {
				List<Optional<Dependente>> dependentesOptionais = dependenteRepository.findByCpfIgnoreCase(d.getCpf());
				if (!CollectionUtils.isEmpty(dependentesOptionais)) {
					throw new ExistingDependentWithSameCPFException("Já existe um dependente cadastrado com este cpf!!");
				}
			}
			
			if (d.getTitular() == null) {
				d.setTitular(clienteSaved.get());
			}
		});
	}
	
	
	private void verififyDependentsBeforeSave(@Valid Cliente cliente) {
		cliente.getDependentes().forEach(d -> {
			if (d.getId() == null) {
				List<Optional<Dependente>> dependentesOptionais = dependenteRepository.findByCpfIgnoreCase(d.getCpf());
				if (!CollectionUtils.isEmpty(dependentesOptionais)) {
					throw new ExistingDependentWithSameCPFException("Já existe um dependente cadastrado com este cpf!!");
				}
			}
			if (d.getTitular() == null) {
				d.setTitular(cliente);
			}
		});
		
	}
	
	
}
