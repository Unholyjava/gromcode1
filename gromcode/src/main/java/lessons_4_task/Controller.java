package lessons_4_task;

import java.sql.SQLException;

public class Controller {
	
	private CommonDAO commonDao;
	
	public Controller(CommonDAO commonDao) {
		super();
		this.commonDao = commonDao;
	}

	public void put(Storage storage, File file) throws SQLException {
		try {
			ValidatorInputData validatorID = new ValidatorInputData();
			if (validatorID.isInputDataCorrect(storage, file)) {
				commonDao.updateIdStorage(file, storage);
			}
		} catch (SQLException e) {
			System.out.println("not put File with ID = " + file.getId() 
				+ " in to Storage with ID = " + storage.getId());
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the commonDao
	 */
	public CommonDAO getCommonDao() {
		return commonDao;
	}
			
}
