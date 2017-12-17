package a2lend.app.com.a2lend;

/**
 * Created by Igbar on 12/12/2017.
 */

public class ObjectToolItem {
    public String _id;
    public String name;
    public String des;
    public String dateAdd;
    public String pictureLink;
    ObjectToolItem(String ToolID, String ToolName, String ToolDes, String DateAdd, String PictureLink){
        this._id=ToolID;
        this.name=ToolName;
        this.des=ToolDes;
        this.dateAdd=DateAdd;
        this.pictureLink=PictureLink;
    }
    ObjectToolItem(ObjectToolItem obj) {
        this._id=obj._id;
        this.name=obj.name;
        this.des=obj.des;
        this.dateAdd=obj.dateAdd;
        this.pictureLink=obj.pictureLink;
    }
}
