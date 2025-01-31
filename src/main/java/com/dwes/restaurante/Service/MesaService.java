package com.dwes.restaurante.Service;

import com.dwes.restaurante.Repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesaService {
    @Autowired
    private MesaRepository mesaRepository;
}
