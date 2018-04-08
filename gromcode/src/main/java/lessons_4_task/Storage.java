package lessons_4_task;

import java.util.Arrays;

public class Storage {

	private long id;
	private File[] files;
	private String[] formatsSupported;
	private String storageCountry;
	private long storageSize;
	public Storage(long id, String[] formatsSupported, String storageCountry, long storageSize) {
		super();
		this.id = id;
		this.formatsSupported = formatsSupported;
		this.storageCountry = storageCountry;
		this.storageSize = storageSize;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the files
	 */
	public File[] getFiles() {
		return files;
	}
	/**
	 * @param files the files to set
	 */
	void setFiles(File[] files) {
		this.files = files;
	}
	/**
	 * @return the formatsSupported
	 */
	public String[] getFormatsSupported() {
		return formatsSupported;
	}
	
	public String getStringFormatsSupported() {
		StringBuilder sb = new StringBuilder();
		for (String format : getFormatsSupported()) {
			sb.append(format + ", ");
		}
		return sb.toString().substring(0, sb.toString().length() - 2);
	}
	
	/**
	 * @return the storageCountry
	 */
	public String getStorageCountry() {
		return storageCountry;
	}
	/**
	 * @return the storageSize
	 */
	public long getStorageSize() {
		return storageSize;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Storage [id=" + id + ", files=" + Arrays.toString(files) + ", formatsSupported="
				+ Arrays.toString(formatsSupported) + ", storageCountry=" + storageCountry + ", storageSize="
				+ storageSize + "]";
	}
	

}
