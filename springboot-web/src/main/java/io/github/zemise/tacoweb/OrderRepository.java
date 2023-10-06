package io.github.zemise.tacoweb;

import io.github.zemise.tacoweb.domain.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
