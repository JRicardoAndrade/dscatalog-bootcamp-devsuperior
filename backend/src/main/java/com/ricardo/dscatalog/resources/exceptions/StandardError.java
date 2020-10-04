package com.ricardo.dscatalog.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

/*Classe que receberá a exceção da camada de serviço e realizará o tratamento retornando o erro como 
 * PÁGINA NÃO ENCOTRADA evitando assim que estoure o erro 500 na aplicação e sendo mais "informativo" ao usuário 
 */


public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String mensagem;
	private String path;
	
	public StandardError() {
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}	
}
