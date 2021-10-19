package com.stacktrace.Stacktrace.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.stacktrace.Stacktrace.model.Billetera;
import com.stacktrace.Stacktrace.model.Operacion;
import com.stacktrace.Stacktrace.model.SaldoDivisa;
import com.stacktrace.Stacktrace.model.Usuario;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class QueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Lista una divisa en todas las billeteras
    @Transactional
    public List<SaldoDivisa> findByTipo(String divisaTipo) {
        String query = "FROM SaldoDivisa WHERE divisaTipo = :divisaTipo";

        @SuppressWarnings("unchecked")
        List<SaldoDivisa> lista = (List<SaldoDivisa>) entityManager.createQuery(query)
                .setParameter("divisaTipo", divisaTipo).getResultList();

        return lista;
    }

    // Lista las divisas de una billetera
    @Transactional
    public List<SaldoDivisa> findByBilletera(Long billeteraId) {
        String query = "FROM SaldoDivisa WHERE billeteraId = :billeteraId";

        @SuppressWarnings("unchecked")
        List<SaldoDivisa> lista = (List<SaldoDivisa>) entityManager.createQuery(query)
                .setParameter("billeteraId", billeteraId).getResultList();

        return lista;
    }

    // Lista de billeteras de un usuario
    @Transactional
    public List<Billetera> findByUserId(Long userId) {
        String query = "FROM Billetera WHERE userId = :userId";

        @SuppressWarnings("unchecked")
        List<Billetera> lista = (List<Billetera>) entityManager.createQuery(query).setParameter("userId", userId)
                .getResultList();
        return lista;

    }

    // Busca un usuario por el email
    @Transactional
    public Usuario findByEmail(String email) {
        String query = "FROM Usuario WHERE email = :email";

        @SuppressWarnings("unchecked")
        List<Usuario> lista = (List<Usuario>) entityManager.createQuery(query).setParameter("email", email)
                .getResultList();

        if (lista.isEmpty())
            return null;
        else
            return lista.get(0);
    }

    public SaldoDivisa getSaldo(Long billeteraId, String divisaTipo) {
        String query = "FROM SaldoDivisa WHERE billeteraId = :billeteraId AND divisaTipo = :divisaTipo";

        @SuppressWarnings("unchecked")
        List<SaldoDivisa> lista = (List<SaldoDivisa>) entityManager.createQuery(query)
                .setParameter("billeteraId", billeteraId).setParameter("divisaTipo", divisaTipo).getResultList();

        if (lista.isEmpty())
            return null;
        else
            return lista.get(0);
    }

    public List<Operacion> getIntercambios() {
        String query = "FROM Operacion WHERE opTipo = 'INTERCAMBIO'";

        @SuppressWarnings("unchecked")
        List<Operacion> lista = (List<Operacion>) entityManager.createQuery(query).getResultList();
        return lista;
    }

    public List<Operacion> getDepositos() {
        String query = "FROM Operacion WHERE opTipo = 'DEPOSITO'";

        @SuppressWarnings("unchecked")
        List<Operacion> lista = (List<Operacion>) entityManager.createQuery(query).getResultList();
        return lista;
    }

    public float getSaldoUsuario(Long userId) {
        
        String query = "SELECT SUM(saldoTotal) FROM Billetera WHERE userId = :userId";

        Object result = entityManager.createQuery(query).setParameter("userId", userId).getSingleResult();
        return Float.valueOf(result.toString());
    }

}