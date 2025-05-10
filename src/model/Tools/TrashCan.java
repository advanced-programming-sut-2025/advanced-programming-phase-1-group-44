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
        this.trashCanType = Material.normal;
        this.level = 0;
        this.type = Tooltype.trashCan;
    }

    public Result remove(Item item , int cnt){
        int price = item.price * cnt;
        int recycle = 15 * trashCanType.hardness;
        Map<String, Object> data = new HashMap<>();
        data.put("flg", true);
        data.put("message", "item removed successfully");
        data.put("money", (Integer)price * recycle / 100);
        return new Result(data);
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
