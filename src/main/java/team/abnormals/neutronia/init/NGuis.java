package team.abnormals.neutronia.init;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.gui.GuiProviderRegistry;
import team.abnormals.neutronia.client.gui.SawmillScreen;

public class NGuis implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        GuiProviderRegistry.INSTANCE.registerFactory(NContainerTypes.SAWMILL_CONTAINER, SawmillScreen::new);
    }

}