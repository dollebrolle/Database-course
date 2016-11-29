package se.plushogskolan.taskhandler.repositoryImpl;

import se.plushogskolan.taskhandler.assets.enums.WorkItemStatus;
import se.plushogskolan.taskhandler.assets.interfaces.ObjectMapper;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.repository.WorkitemRepository;

public class WorkItemRepositoryImpl extends BaseCRUDRepository<WorkItem> implements WorkitemRepository {

	private final ObjectMapper<WorkItem> WORKITEM_MAPPER = (w) -> {
		WorkItem workItem = new WorkItem(w.getString("name"), WorkItemStatus.valueOf(w.getString("status")),
				Integer.parseInt(w.getString("userId")));
		workItem.setId(Integer.parseInt(w.getString("id")));
		return workItem;
	};
	
	@Override
	protected String getTableName() {
		return "Workitem";
	}

	@Override
	protected ObjectMapper<WorkItem> getMapper() {
		return WORKITEM_MAPPER;
	}

}