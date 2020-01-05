package com.tddcourse.tdd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MockitoTests {

    @Mock
    List<String> lista;

    @Test
    public void primeiroTesteMockito () {
        Mockito.when(lista.size()).thenReturn(20);

        int size = lista.size();

        Assertions.assertThat(size).isEqualTo(20);
        Mockito.verify(lista, Mockito.times(1)).size();
    }

    @Test
    public void implementacaoDoInOrderMockito () {
        lista.size();
        lista.add("");

        InOrder inOrder = Mockito.inOrder(lista);
        inOrder.verify(lista).size();
        inOrder.verify(lista).add("");
    }
}
