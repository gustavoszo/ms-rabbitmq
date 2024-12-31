package br.com.alurafood.pagamentos.clients;

import br.com.alurafood.pagamentos.clients.dto.ReponseOrderExistsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "pedidos")
public interface OrderClient {

    @RequestMapping(method = RequestMethod.GET, value = "/orders/{id}/exists")
    ReponseOrderExistsDto orderExists(@PathVariable Long id);

}
