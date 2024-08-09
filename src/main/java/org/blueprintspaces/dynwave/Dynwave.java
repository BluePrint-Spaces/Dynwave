package org.blueprintspaces.dynwave;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.blueprintspaces.dynwave.commands.TeamOrganizerCommand;
import org.blueprintspaces.dynwave.init.BlocksInit;
import org.blueprintspaces.dynwave.init.DynWaveGroup;
import org.blueprintspaces.dynwave.init.EntityInit;
import org.blueprintspaces.dynwave.init.ItemInit;

public class Dynwave implements ModInitializer {


    public static final String MOD_ID = "dynwave";
    public static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void onInitialize() {
        BlocksInit.registerModBlocks();
        TeamOrganizerCommand.initialize();

        DynWaveGroup.registerItemGroups();
        ItemInit.registerModItems();
        EntityInit.registerModEntities();
    }
}
