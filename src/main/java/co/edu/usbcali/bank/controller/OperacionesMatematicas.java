package co.edu.usbcali.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mat/")
public class OperacionesMatematicas {

	@GetMapping("sumar/{n1}/{n2}")
	public Resultado sumar(@PathVariable("n1") Integer n1, @PathVariable("n2") Integer n2) {
		return new Resultado(n1 + n2);
	}

	@GetMapping("restar/{n1}/{n2}")
	public Resultado restar(@PathVariable("n1") Integer n1, @PathVariable("n2") Integer n2) {
		return new Resultado(n1 - n2);
	}
	
	@GetMapping("dividir/{n1}/{n2}")
	public Resultado div(@PathVariable("n1") Integer n1, @PathVariable("n2") Integer n2) {
		if(n2 == 0) {
			return new Resultado(0);
		}
		return new Resultado(n1 / n2);
	}
}

class Resultado {
	private Integer valor;
	
	public Resultado(Integer valor) {
		super();
		this.valor = valor;
		
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
