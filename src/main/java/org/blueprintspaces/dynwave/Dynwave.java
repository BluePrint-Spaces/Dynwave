package org.blueprintspaces.dynwave;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.blueprintspaces.dynwave.init.BlocksInit;

public class Dynwave implements ModInitializer {


    public static final String MOD_ID = "dynwave";
    public static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void onInitialize() {
        BlocksInit.registerModBlocks();
    }
}
