package org.progII.dao;

import org.progII.entities.Consultorio;
import org.progII.interfaces.AdmConection;
import org.progII.interfaces.DAO;

import java.util.ArrayList;
import java.util.List;

public class ConsultorioDAO implements AdmConection, DAO<Consultorio, Integer> {

    private static final String sql_instert = "INSERT INTO consultorio_db.consultorio (medico) VALUES (?)";
    private static final String sql_update = "UPDATE consultorio_db.consultorio SET medico = ? WHERE id = ?";
    private static final String sql_delete = "DELETE * FROM consultorio_db.consultorio WHERE id = ?";
    private static final String sql_getbyid = "SELECT * FROM consultorio_db.consultorio WHERE id = ?";
    private static final String sql_getall = "SELECT * FROM consultorio_db.consultorio ORDER BY medico";

    @Override
    public List<Consultorio> getAll() {
        List<Consultorio> consultorios = new ArrayList<>();
        return List.of();
    }

    @Override
    public void insert(Consultorio objeto) {

    }

    @Override
    public void update(Consultorio objeto) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Consultorio getById(Integer id) {
        return null;
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }
}
