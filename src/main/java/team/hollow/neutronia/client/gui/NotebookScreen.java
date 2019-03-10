package team.hollow.neutronia.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.InputListener;
import net.minecraft.client.gui.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import team.hollow.neutronia.Neutronia;
import team.hollow.neutronia.api.INotebookElement;
import team.hollow.neutronia.api.INotebookSection;
import team.hollow.neutronia.init.ArcaneMagicConstants;
import team.hollow.neutronia.network.ArcaneMagicPacketHandler;
import team.hollow.neutronia.network.NotebookUpdatePacket;
import team.hollow.neutronia.notebook.ContentsNotebookSection;
import team.hollow.neutronia.notebook.NotebookSectionRegistry;
import team.hollow.neutronia.utils.DataHolder;
import team.hollow.neutronia.utils.RenderUtils;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class NotebookScreen extends Screen
{
	private INotebookSection section;
	private int leftPage = 0;
	private int scaledMouseX = 0;
	private int scaledMouseY = 0;

	private List<INotebookElement> leftElements = new ArrayList<>();
	private List<INotebookElement> rightElements = new ArrayList<>();

	public NotebookScreen(ItemStack stack)
	{
		CompoundTag tag = stack.getTag();
		if (tag != null && tag.containsKey(ArcaneMagicConstants.NOTEBOOK_SECTION_KEY))
		{
			INotebookSection section = NotebookSectionRegistry.get(Identifier.create(tag.getString(ArcaneMagicConstants.NOTEBOOK_SECTION_KEY)));
			int page = tag.getInt(ArcaneMagicConstants.NOTEBOOK_PAGE_KEY);

			this.section = section;

			if (section != null)
			{
				this.leftPage = page;
			}
		}
	}

	private void setSection(INotebookSection section)
	{
		this.leftPage = 0;
        this.section = NotebookSectionRegistry.CONTENTS;
		this.leftElements.clear();
		this.rightElements.clear();

		this.leftElements = this.section.getElements((DataHolder) client.player, 0);
		this.rightElements = this.section.getElements((DataHolder) client.player, 1);
	}

	private void pageChanged()
	{
		this.leftElements.clear();
		this.rightElements.clear();

		this.leftElements = this.section.getElements((DataHolder) client.player, this.leftPage);
		this.rightElements = this.section.getElements((DataHolder) client.player, this.leftPage + 1);
	}

	@Override
	protected void onInitialized()
	{
		super.onInitialized();
		if (section != null)
		{
			int page = leftPage;
			setSection(section);
			if (page <= section.getPageCount((DataHolder) client.player))
			{
				this.leftPage = page;
				pageChanged();
			}
		} else
		{
			Neutronia.getLogger().warn("Tried to open a notebook with invalid NBT !");
			setSection(NotebookSectionRegistry.CONTENTS);
		}
		this.listeners.add(new InputListener()
		{
			@Override
			public boolean mouseClicked(double mouseX, double mouseY, int button)
			{
				if (button == 0)
				{
					if (leftPage + 1 < section.getPageCount((DataHolder) client.player) && overRightArrow())
					{
						leftPage += 2;
						pageChanged();
						client.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1, 1);
						return true;
					} else if (leftPage > 0 && overLeftArrow())
					{
						leftPage -= 2;
						if (leftPage < 0)
						{
							leftPage = 0;
						}
						pageChanged();
						client.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1, 1);
						return true;
					} else if (!(section instanceof ContentsNotebookSection) && overBackArrow())
					{
						setSection(NotebookSectionRegistry.CONTENTS);
						client.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1, 1);
						return true;
					} else if (handleClickOn(leftElements) || handleClickOn(rightElements))
					{
						client.player.playSound(SoundEvents.UI_BUTTON_CLICK, 0.5f, 1);
						return true;
					}
				} else if (button == 1)
				{
					if (section != NotebookSectionRegistry.CONTENTS)
					{
						setSection(NotebookSectionRegistry.CONTENTS);
						client.player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN, 1, 1);
						return true;
					}
				}
				return false;
			}
		});
	}

	private boolean isMouseOverAny(List<INotebookElement> elements)
	{
		for (INotebookElement element : elements)
		{
			if (element.mouseOver(scaledMouseX, scaledMouseY))
			{
				return true;
			}
		}
		return false;
	}

	private boolean handleClickOn(List<INotebookElement> elements)
	{
		for (INotebookElement element : elements)
		{
			INotebookSection s = element.handleClick(scaledMouseX, scaledMouseY);
			if (s != null)
			{
				setSection(s);
				return true;
			}
		}
		return false;
	}

	private boolean overRightArrow()
	{
		int xTop = (client.window.getScaledWidth() / 2) - (ArcaneMagicConstants.NOTEBOOK_WIDTH / 2);
		int yTop = (client.window.getScaledHeight() / 2) - (ArcaneMagicConstants.NOTEBOOK_HEIGHT / 2);

		int right = xTop + 142;

		return scaledMouseX >= right + 85 && scaledMouseY >= yTop + ArcaneMagicConstants.NOTEBOOK_HEIGHT - 21 && scaledMouseX <= right + 103 && scaledMouseY <= yTop + ArcaneMagicConstants.NOTEBOOK_HEIGHT - 11;
	}

	private boolean overLeftArrow()
	{
		int xTop = (client.window.getScaledWidth() / 2) - (ArcaneMagicConstants.NOTEBOOK_WIDTH / 2);
		int yTop = (client.window.getScaledHeight() / 2) - (ArcaneMagicConstants.NOTEBOOK_HEIGHT / 2);

		int left = xTop + 17;

		return scaledMouseX >= left + 10 && scaledMouseY >= yTop + ArcaneMagicConstants.NOTEBOOK_HEIGHT - 21 && scaledMouseX <= left + 28 && scaledMouseY <= yTop + ArcaneMagicConstants.NOTEBOOK_HEIGHT - 11;
	}

	private boolean overBackArrow()
	{
		//right + 85, yTop + ArcaneMagicConstants.NOTEBOOK_HEIGHT - 21
		int xTop = (client.window.getScaledWidth() / 2) - (ArcaneMagicConstants.NOTEBOOK_WIDTH / 2);
		int yTop = (client.window.getScaledHeight() / 2) - (ArcaneMagicConstants.NOTEBOOK_HEIGHT / 2);

		int right = xTop + 142;
		return scaledMouseX >= right - 15 && scaledMouseY >= yTop + ArcaneMagicConstants.NOTEBOOK_HEIGHT - 21 && scaledMouseX <= right && scaledMouseY <= yTop + ArcaneMagicConstants.NOTEBOOK_HEIGHT - 10;
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks)
	{
		this.drawBackground();
		super.draw(mouseX, mouseY, partialTicks);

		this.scaledMouseX = mouseX;
		this.scaledMouseY = mouseY;

		GlStateManager.pushMatrix();

		int xTop = (client.window.getScaledWidth() / 2) - (ArcaneMagicConstants.NOTEBOOK_WIDTH / 2);
		int yTop = (client.window.getScaledHeight() / 2) - (ArcaneMagicConstants.NOTEBOOK_HEIGHT / 2);

		int left = xTop + 17;
		int right = xTop + 142;

		client.getTextureManager().bindTexture(ArcaneMagicConstants.NOTEBOOK_TEXTURE);
		DrawableHelper.drawTexturedRect(xTop, yTop, 0, 0, ArcaneMagicConstants.NOTEBOOK_WIDTH, ArcaneMagicConstants.NOTEBOOK_HEIGHT, ArcaneMagicConstants.NOTEBOOK_WIDTH, ArcaneMagicConstants.NOTEBOOK_HEIGHT, ArcaneMagicConstants.NOTEBOOK_WIDTH, ArcaneMagicConstants.NOTEBOOK_TEX_HEIGHT);

		if (section instanceof ContentsNotebookSection)
		{
			DrawableHelper.drawTexturedRect(xTop + 133, yTop + 156, 136, 180, 5, 11, 5, 11, ArcaneMagicConstants.NOTEBOOK_WIDTH, ArcaneMagicConstants.NOTEBOOK_TEX_HEIGHT);
		}

		// Intro page
		int pointer = yTop + 15;
		for (INotebookElement element : this.leftElements)
		{
			GlStateManager.pushMatrix();
			pointer += element.draw(this, left, pointer, mouseX, mouseY, xTop, yTop);
			GlStateManager.popMatrix();
		}

		pointer = yTop + 15;
		for (INotebookElement element : this.rightElements)
		{
			GlStateManager.pushMatrix();
			pointer += element.draw(this, right, pointer, mouseX, mouseY, xTop, yTop);
			GlStateManager.popMatrix();
		}

		client.getTextureManager().bindTexture(ArcaneMagicConstants.NOTEBOOK_TEXTURE);

		if (leftPage + 1 < section.getPageCount((DataHolder) client.player))
		{
			RenderUtils.drawTexturedRect(right + 85, yTop + ArcaneMagicConstants.NOTEBOOK_HEIGHT - 21, overRightArrow() ? 23 : 0, 180, 18, 10, 18, 10, ArcaneMagicConstants.NOTEBOOK_WIDTH, ArcaneMagicConstants.NOTEBOOK_TEX_HEIGHT);
		}

		if (leftPage > 0)
		{
			RenderUtils.drawTexturedRect(left + 10, yTop + ArcaneMagicConstants.NOTEBOOK_HEIGHT - 21, overLeftArrow() ? 23 : 0, 193, 18, 10, 18, 10, ArcaneMagicConstants.NOTEBOOK_WIDTH, ArcaneMagicConstants.NOTEBOOK_TEX_HEIGHT);
		}

		if (!(section instanceof ContentsNotebookSection))
		{
			RenderUtils.drawTexturedRect(right - 15, yTop + ArcaneMagicConstants.NOTEBOOK_HEIGHT - 21, overBackArrow() ? 66 : 46, 193, 15, 11, 15, 11, ArcaneMagicConstants.NOTEBOOK_WIDTH, ArcaneMagicConstants.NOTEBOOK_TEX_HEIGHT);
		}

		for (INotebookElement element : this.leftElements)
		{
			GlStateManager.pushMatrix();
			element.drawOverlay(this, mouseX, mouseY, xTop, yTop);
			GlStateManager.popMatrix();
		}

		for (INotebookElement element : this.rightElements)
		{
			GlStateManager.pushMatrix();
			element.drawOverlay(this, mouseX, mouseY, xTop, yTop);
			GlStateManager.popMatrix();
		}

		GlStateManager.popMatrix();
	}

	@Override
	public void onClosed()
	{
		if (this.section != null)
		{
			ArcaneMagicPacketHandler.sendToServer(new NotebookUpdatePacket(this.section.getID().toString(), this.leftPage));
		}
	}

	@Override
	public boolean isPauseScreen()
	{
		return false;
	}
}