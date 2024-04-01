package com.assinatura.assinatura;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assinatura.assinatura.models.Author;
import com.assinatura.assinatura.models.Document;
import com.assinatura.assinatura.models.Signature;

public class AssinaturaApplication {
	/**
	 * Logger para mostrar logs de acesso.
	 */
	private static final Logger log = LoggerFactory.getLogger(AssinaturaApplication.class);

	public static void main(String[] args) {
		Author author1 = new Author(1l);
		Author author2 = new Author(2l);

		Document document1 = new Document(1l, author1, "Enviado por 1");
		Document document2 = new Document(2l, author1, "Enviado por 1");

		Signature signature1 = new Signature(1l, author1, document1);
		log.info("signature is valid: {}", signature1.validate()); // VALID

		signature1.setAuthor(author2);
		log.info("signature is valid: {}", signature1.validate()); // COULD NOT DECRYPT USING PUBLIC

		document1.setContent("null");
		log.info("signature is valid: {}", signature1.validate()); // DOCUMENT IS INVALID (CONTENT TEMPERED)

		signature1.setAuthor(author1);
		signature1.setDocument(document2);
		log.info("signature is valid: {}", signature1.validate()); // VALID (Deveria ser inválido. para conseguir isso, o
																																// hash gerado para o documento deve incluir também,
																																// além do conteúdo, o ID ou outro identificador único)
	}
}
