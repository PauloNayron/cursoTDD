package com.tddcourse.tdd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CadastroPessoaTest {

    @Test
    @DisplayName("Deve Criar o Cadastro de pessoas.")
    public void deveCriarOCadastroDePessoas () {
        // cenário e execução
        CadastroPessoa cadastro = new CadastroPessoa();

        // verificação
        Assertions.assertThat(cadastro.getPessoas()).isEmpty();
    }

    @Test
    @DisplayName("Deve adicionar uma pessoa.")
    public void deveAdicionarUmaPessoa () {
        // cenário
        CadastroPessoa cadastroPessoa = new CadastroPessoa();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paulo Nayron");

        // execução
        cadastroPessoa.adicionar(pessoa);

        // verificação
        Assertions.assertThat(cadastroPessoa.getPessoas())
                .isNotEmpty()
                .hasSize(1)
                .contains(pessoa);
    }

    @Test//(expected = PessoaSemNomeException.class)
    @DisplayName("Não deve cadastrar uma Pessoa com nome vazio.")
    public void naoDeveCadastrarPessoaComNomeVazio () {
        // Cenário
        CadastroPessoa cadastroPessoa = new CadastroPessoa();
        Pessoa pessoa = new Pessoa();

        // execucao
//        cadastroPessoa.adicionar(pessoa);
        org.junit.jupiter.api.Assertions
                .assertThrows(PessoaSemNomeException.class, () -> cadastroPessoa.adicionar(pessoa));

    }

    @Test
    @DisplayName("Deve remover uma pessoa.")
    public void deveRemoverUmaPessoa () {
        // cenário
        CadastroPessoa cadastroPessoa = new CadastroPessoa();
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paulo Nayron");
        cadastroPessoa.adicionar(pessoa);

        // execução
        cadastroPessoa.remover(pessoa);

        // verificação
        Assertions.assertThat(cadastroPessoa.getPessoas()).isEmpty();
    }

    @Test//(expected = CadastroPessoaVazioException.class)
    @DisplayName("Deve lançar erro ao tentar remover pessoa vazia.")
    public void deveLancarErroAoTentarRemoverPessoaInexistente () {
        // Cenario
        CadastroPessoa cadastroPessoa = new CadastroPessoa();
        Pessoa pessoa = new Pessoa();

        // execucao
//        cadastroPessoa.remover(pessoa);

        org.junit.jupiter.api.Assertions
                .assertThrows(CadastroPessoaVazioException.class, () -> cadastroPessoa.remover(pessoa));
    }

}
