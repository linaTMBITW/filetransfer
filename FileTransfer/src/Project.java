
public class Project {
	String project,id,name,classname,monitorid,monitorname;
	public Project(String project, String id, String name, String classname, String monitorid, String monitorname){
		this.project=project;
		this.id=id;
		this.name=name;
		this.classname=classname;
		this.monitorid=monitorid;
		this.monitorname=monitorname;
	}
	public String getProject(){
		return project;
	}
	public String getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getClassname(){
		return classname;
	}
	public String getMonitorid(){
		return monitorid;
	}
	public String getMonitorname(){
		return monitorname;
	}
}
