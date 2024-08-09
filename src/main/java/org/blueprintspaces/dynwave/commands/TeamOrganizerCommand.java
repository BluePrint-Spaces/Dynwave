package org.blueprintspaces.dynwave.commands;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.command.CommandSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class TeamOrganizerCommand
{
    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, registrationEnvironment) -> {
            dispatcher.register(CommandManager.literal("organizeTeams")
                    .requires(source -> source.hasPermissionLevel(2))  // Set permission level to 2
                    .executes(context -> {
                        return organizeTeams(context.getSource());
                    })
            );
        });
    }

    private static int organizeTeams(ServerCommandSource source) {
        List<ServerPlayerEntity> players = source.getServer().getPlayerManager().getPlayerList();

        if (players.size() < 4) {
            source.sendFeedback((Supplier<Text>) Text.of("Not enough players to organize into 4 teams."), false);
            return 0;
        }

        Collections.shuffle(players);
        int teamSize = players.size() / 4;
        List<List<ServerPlayerEntity>> teams = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            teams.add(new ArrayList<>(players.subList(i * teamSize, (i + 1) * teamSize)));
        }

        for (int i = 0; i < teams.size(); i++) {
            List<ServerPlayerEntity> team = teams.get(i);
            for (ServerPlayerEntity player : team) {
                player.sendMessage(Text.of("You are in team " + (i + 1)), true);
            }
        }

        source.sendFeedback((Supplier<Text>) Text.of("Teams have been successfully organized."), true);
        return 1;  // Return success
    }
}
