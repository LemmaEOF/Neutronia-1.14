package team.hollow.neutronia.notebook;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.api.INotebookElement;
import team.hollow.neutronia.api.INotebookSection;

import java.util.ArrayList;
import java.util.List;

public class SmeltingNotebookSection implements INotebookSection {
    @Override
    public Identifier getID() {
        return new Identifier(Neutronia.MOD_ID, "smelting");
    }

    @Override
    public List<INotebookElement> getElements(ClientPlayerEntity player, int page) {
        List<INotebookElement> elements = new ArrayList<>();

        if (page == 0) {
            elements.add(new NotebookElement.SmallHeading("notebook.arcanemagic.smelting.title").withPadding(3));
        } else {
            elements.add(new NotebookElement.Padding(3));
        }

        int firstText = NotebookElement.textPages("notebook.arcanemagic.smelting.0", 2);
        elements.addAll(NotebookElement.wrapText("notebook.arcanemagic.smelting.0", 2, 0, page));

        if (page == firstText + 1) {
            elements.add(new NotebookElement.Padding(4));
            elements.add(new NotebookElement.Paragraph(true, 1, "block.arcanemagic.smelter").withPadding(10));
            elements.add(new NotebookElement.Recipe(MinecraftClient.getInstance().world.getRecipeManager().get(new Identifier(Neutronia.MOD_ID, "smelter")).orElse(null)));
        }

        if (page >= firstText + 2) {
            elements.addAll(NotebookElement.wrapText("notebook.arcanemagic.smelting.1", 0, firstText + 2, page));
        }
        return elements;
    }

    @Override
    public int getPageCount(ClientPlayerEntity player) {
        return NotebookElement.textPages("notebook.arcanemagic.smelting.0", 2) + NotebookElement.textPages("notebook.arcanemagic.smelting.1", 0) + 2;
    }
}