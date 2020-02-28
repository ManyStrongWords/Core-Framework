package com.openrsc.server.model.container;

import com.openrsc.server.model.entity.player.Player;
import java.util.concurrent.atomic.AtomicReference;

public class CarriedItems {
	//<editor-fold desc="Class Members">
	/**
	 * List of items in the inventory container
	 */
	private Inventory inventory;

	/**
	 * List of items in the equipment container
	 *
	 */
	private Equipment equipment;

	/**
	 * Reference back to the player who owns these items
	 */
	private final Player player;
	//</editor-fold>
	//<editor-fold desc="Constructors">
	public CarriedItems(Player player) {
		this.player = player;
	}
	//</editor-fold>
	//<editor-fold desc="Class Member 'Setters'">
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	//</editor-fold>
	//<editor-fold desc="Class Member 'Getters'">
	public Inventory getInventory() {
		return this.inventory;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}
	//</editor-fold>
	//<editor-fold desc="Class Methods">

	/**
	 * Searches both the inventory and equipment for a specific
	 * catalog ID. Can specify if notes are allowed.
	 * @param catalogID: item being searched for
	 * param allowNoted: specifies if that item can be noted or not
	 * @return BOOLEAN
	 */
	//TODO: Add parameter allowNoted
	public boolean hasCatalogID(final int catalogID) {
		if (getInventory().hasCatalogID(catalogID))
			return true;
		else
			return getEquipment().hasCatalogID(catalogID);
	}

	public int remove(int catalogID, int amount, boolean updateClient) {
		int result = getInventory().remove(catalogID, amount, updateClient);
		if (result == -1)
			return getEquipment().remove(catalogID, amount);

		return result;
	}
	//</editor-fold>
}