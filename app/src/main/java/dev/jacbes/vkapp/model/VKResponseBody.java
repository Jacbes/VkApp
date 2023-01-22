package dev.jacbes.vkapp.model;

import java.util.List;

/*
    Body содержащий в себе колличество друзей и их список.
 */
public class VKResponseBody {
    Integer count;
    List<VKUser> items;

    public VKResponseBody() {
    }

    public Integer getCount() {
        return count;
    }

    public List<VKUser> getItems() {
        return items;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setItems(List<VKUser> items) {
        this.items = items;
    }
}
