package processa.filesearcher.model;

import java.nio.file.Path;
import processa.filesearcher.exceptions.SearcherException;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class DataRequest {

	private SearchAction action;
	private String fieldContent;
	private Integer fieldNumber;
	private Integer messageType;

	private Path path;

	private String structuredDataContent;

	private String structuredDataKey;

	private Integer threshold;

	/**
	 * @return action
	 */
	public SearchAction getAction() {
		return this.action;
	}

	/**
	 * @return fieldContent
	 */
	public String getFieldContent() {
		return this.fieldContent;
	}

	/**
	 * @return fieldNumber
	 */
	public Integer getFieldNumber() {
		return this.fieldNumber;
	}

	/**
	 * @return messageType
	 */
	public Integer getMessageType() {
		return this.messageType;
	}

	/**
	 * @return path
	 */
	public Path getPath() {
		return this.path;
	}

	/**
	 * @return structuredDataContent
	 */
	public String getStructuredDataContent() {
		return this.structuredDataContent;
	}

	/**
	 * @return structuredDataKey
	 */
	public String getStructuredDataKey() {
		return this.structuredDataKey;
	}

	/**
	 * @return threshold
	 */
	public Integer getThreshold() {
		return this.threshold;
	}

	/**
	 * @param action
	 */
	public void setAction(SearchAction action) {
		this.action = action;
	}

	/**
	 * @param fieldContent
	 */
	private void setFieldContent(String fieldContent) {
		this.fieldContent = fieldContent;
	}

	/**
	 * @param fieldNumber
	 */
	private void setFieldNumber(Integer fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	/**
	 * @param messageType
	 */
	private void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	/**
	 * @param path
	 */
	public void setPath(Path path) {
		this.path = path;
	}

	/**
	 * @param structuredDataContent
	 */
	private void setStructuredDataContent(String structuredDataContent) {
		this.structuredDataContent = structuredDataContent;
	}

	/**
	 * @param structuredDataKey
	 */
	private void setStructuredDataKey(String structuredDataKey) {
		this.structuredDataKey = structuredDataKey;
	}

	/**
	 * @param threshold
	 */
	private void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	/**
	 * @throws SearcherException
	 */
	public void validate() throws SearcherException {
		if (this.getPath() == null) {
			throw new SearcherException(
					String.format("Campo Obligatorio. Elija el directorio al que le desea realizar la búsqueda."));
		}
		SearchAction action = this.getAction();
		Input[] inputs = action.getInputs();
		for (Input input : inputs) {
			Object validate = input.validate();
			try {
				switch (input) {
				case FIELD_CONTENT:
					this.setFieldContent((String) validate);
					break;
				case FIELD_NUMBER:
					this.setFieldNumber((Integer) validate);
					break;
				case MESSAGE_TYPE:
					this.setMessageType((Integer) validate);
					break;
				case SD_KEY:
					this.setStructuredDataKey((String) validate);
					break;
				case SD_VALUE:
					this.setStructuredDataContent((String) validate);
					break;
				case SEARCH_LIMIT:
					this.setThreshold((Integer) validate);
					break;
				default:
					throw new SearcherException("Error procesando la acción a ejecutar");
				}
			} catch (ClassCastException e) {
				throw new SearcherException("Error procesando la acción solicitada. ClassCastException");
			}
		}
	}
}
