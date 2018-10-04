package com.kamai.antiauction.model.transfer.view;

public class View {

    /**
     * Returning to client side only id
     */
    public interface IdView {}
    /**
     * Interface created for marking fields of DTO and knowing what would be return into client side
     * The interface describes a short view of any DTO
     */
    public interface ShortView extends IdView {}

    /**
     * Interface created for marking fields of DTO and knowing what would be return into client side
     * The interface describes a full view of any DTO
     */
    public interface FullView extends ShortView{}
}
