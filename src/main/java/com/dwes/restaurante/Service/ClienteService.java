package com.dwes.restaurante.Service;

import com.dwes.restaurante.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
@Autowired
    private ClienteRepository clienteRepository;

}
