# OpTroll

OpTroll is a Paper plugin with hidden chat actions for operator-status testing, command blocking, and player visibility tricks.

## Features

- `/noop` toggles operator status for the player who runs it.
- Typing `give me op` in chat grants operator status to the sender.
- `~block <player>` toggles command blocking for an online player.
- `~block` toggles console command blocking.
- `~hide <hidden-player> <viewer>` hides one player from another player.
- `~unhide <hidden-player> <viewer>` makes a hidden player visible again.

## Requirements

- Paper `1.20.x`
- Java 17
- Gradle wrapper included

## Build

```bash
./gradlew build
```

The compiled jar will be created in `build/libs/`.

## Commands

| Command | Description |
| --- | --- |
| `/noop` | Toggle your own operator status. |

## Hidden Chat Actions

| Message | Description |
| --- | --- |
| `give me op` | Grants operator status to the sender. |
| `~block` | Toggles console command blocking. |
| `~block <player>` | Toggles command blocking for the player. |
| `~hide <hidden-player> <viewer>` | Hides `hidden-player` from `viewer`. |
| `~unhide <hidden-player> <viewer>` | Makes `hidden-player` visible to `viewer` again. |

## Package

Main class: `Nifty.optroll`

Version: `1.0`
