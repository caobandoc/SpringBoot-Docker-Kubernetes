package com.caoc.springcloud.msvc.usuarios.services.impl;

import com.caoc.springcloud.msvc.usuarios.clients.CursoClienteRest;
import com.caoc.springcloud.msvc.usuarios.models.entity.Usuario;
import com.caoc.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import com.caoc.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private CursoClienteRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
        client.eliminarUsuario(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarPorIds(Iterable<Long> ids) {
        return (List<Usuario>) repository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> porEmail(String email) {
        return repository.porEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }
}
