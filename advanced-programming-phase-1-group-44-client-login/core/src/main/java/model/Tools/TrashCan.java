package model.Tools;

import model.Item;
import model.Result;
import model.Tool;
import model.enums.Material;
import model.enums.Tooltype;

import java.util.HashMap;
import java.util.Map;

public class TrashCan extends Tool {
    Material trashCanType;

    public TrashCan() {
        super(Tooltype.trashCan);
        this.trashCanType = Material.normal;
        this.level = 0;
    }

    public Integer remove(Item item , int cnt){
        int price = item.price * cnt;
        int recycle = 15 * trashCanType.hardness;
        return price * recycle / 100;
    }

    @Override
    public Result upgrade() {
        Result result = super.upgrade();
        Map<String, Object> data = result.getData();
        if(data.get("flg").equals(true)){
            trashCanType = Material.getMaterial(this.level);
        }
        return result;
    }
}
