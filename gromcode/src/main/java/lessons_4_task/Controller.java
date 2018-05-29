package lessons_4_task;

import java.sql.SQLException;

public class Controller {
	
	private CommonDAO commonDao;
		
	public Controller(CommonDAO commonDao) {
		this.commonDao = commonDao;
	}

	public void put(Storage storage, File file) throws SQLException {
		try {
			if (ValidatorInputData.isInputDataCorrect(storage, file)) {
				commonDao.updateIdStorageFile(file, storage);
			}
		} catch (SQLException e) {
			System.out.println("not put File with ID = " + file.getId() 
				+ " in to Storage with ID = " + storage.getId());
			e.printStackTrace();
		}
	}
	
	public void delete(Storage storage, File file) throws SQLException {
		try {
			if (ValidatorInputData.isIdNotInStorage(storage, file)) {
				throw new Exception("not valid input data");
			} 
			commonDao.deleteFile(file);
		} catch (SQLException e) {
			System.out.println("not delete File with ID = " + file.getId() 
				+ " from Storage with ID = " + storage.getId());
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void transferAll(Storage storageFrom, Storage storageTo) throws SQLException {
		try {
			for (File files : storageFrom.getFiles()) {
				if (!ValidatorInputData.isInputDataCorrect(storageTo, files) ||
						ValidatorInputData.isIdNotInStorage(storageFrom, files)) {
					throw new Exception("not valid input data");
				}
			}
			commonDao.updateIdStorageFileArray(storageFrom.getFiles(), storageTo);
		} catch (SQLException e) {
			System.out.println("not transfer all files from Storage with ID = " + storageFrom.getId() 
				+ " to Storage with ID = " + storageTo.getId());
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void transferFile(Storage storageFrom, Storage storageTo, long id) throws SQLException {
		try {
			for (File file : storageFrom.getFiles()) {
				if (file.getId() == id && ValidatorInputData.isInputDataCorrect(storageTo, file)) {
					commonDao.updateIdStorage(file, storageTo);
				} else {
					throw new Exception("not valid input data");
				}
			}
		} catch (SQLException e) {
			System.out.println("not put File with ID = " + id 
				+ " in to Storage with ID = " + storageTo.getId()
				+ " from Storage with ID = " + storageFrom.getId());
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * @return the commonDao
	 */
	public CommonDAO getCommonDao() {
		return commonDao;
	}
			
}
