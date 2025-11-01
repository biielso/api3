package lab.crud.api.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer tipoUsuario;
    private LocalDate dataNascimento;

    public Usuario() {
    }

    // Getters e Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer gettipoUsuario() {
        return tipoUsuario;
    }

    public void settipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }



    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", tipoUsuario=" + tipoUsuario + 
               ", senha=" + ", dataNascimento=" + dataNascimento + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, tipoUsuario, dataNascimento);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(nome, other.nome) &&
               Objects.equals(tipoUsuario, other.tipoUsuario) &&
               Objects.equals(dataNascimento, other.dataNascimento);
    }
}
