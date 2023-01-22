package dev.jacbes.vkapp.model;

/*
    Класс ответа от VK API, содержащий в себе body.
 */
public class VKResponse {
    VKResponseBody response;

    public VKResponse() {
    }

    public VKResponseBody getResponse() {
        return response;
    }

    public void setResponse(VKResponseBody response) {
        this.response = response;
    }
}

