package fatec;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class TesteAluno {

    @Test
    void test() {
        SalaAula fatec = new SalaAula();

        Aluno ana = new Aluno("Ana Clara", "67890");
        Aluno joao = new Aluno("João Pedro", "67891");

        fatec.cadastrarAluno(ana);
        fatec.cadastrarAluno(joao);

        assertEquals(fatec.getAlunos().size(), 2);

        List<Aluno> encontrar = fatec.buscarAluno("João Pedro");
        assertEquals(encontrar.get(0).getRa(), joao.getRa());
    }
}
