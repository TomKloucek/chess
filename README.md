# Šachy ♟️

<img src="https://images.chesscomfiles.com/uploads/v1/images_users/tiny_mce/SamCopeland/phpuTejFE.gif" alt="chess" width="250"/>

### Autoři
Tomáš Klouček & Vladyslav Babyč

## Téma

V jazyce Java s využitím grafického uživatelského rozhraní (GUI) napište program pro hru šachů pro dva hráče.

## Cíl projektu

Cílem projektu je vytvořit aplikaci umožňující hrát hru šachy dvou hráčům přes síťové rozhraní. Dalším cílem pro studenty je naučit se kooperaci a programování většího projektu, naučit se programování v jazyku Java a grafické rozhraní pomocí frameworků (JavaFX, Swing).

## Základní funkcionalita

- hra dvou hráčů
- člověk vs. člověk
- příprava pro hru člověk vs. počítač
- kompletní kontrola pravidel hry
- možnost uložení a načtení rozehrané partie
- možnost manuálního umístění figur před zahájením hry
- šachové hodiny
- Každý musí implementovat minimálně jednu z následujících vlastností/úkolů:
- síťová hra
- načítání, ukládání a prohlížení partií ve standardním PGN formátu

## Uživatelská dokumentace
V současné době aplikace umožnujě hrát šachy v konzoli, kdy se dva hráči střídají metodou Pass&Play.

Hráč zapne hru, a zadává souřadnice jako figurku chce vybrat a v konzoli se vypíše zda je figurka validní a má možnost se někam pohnout, v případě že, ne, má hráč možnost si vybrat jinou figurku, v opačném případě se vypíšou možnosti kam se figurka může pohnout a hráč zadává finální souřadnice.

<img src="/media/gitlab/console.png" alt="UML" width="488"/>

Budoucím plánem je následující implemetace v grafickém rozhraní viz. níže uvedené ukázky

Naše šachovnice bude vypadat následujicím způsobem

<img src="/media/gitlab/manual/chess_board.jpg" alt="UML" width="400"/>

Po zvolení figurky se na šachovnici zobrazí možné tahy vybrané figurky

<img src="/media/gitlab/manual/chess_board_move_example.jpg" alt="UML" width="400"/>

Pokud figurka bude mít možnost vzít figurku protivníka, bude to znázorněno následujícím způsobem

<img src="/media/gitlab/manual/chess_board_take_example.jpg" alt="UML" width="400"/>

Po zvolení políčka, na které se figurka má posunout kliknutím na jednu z vykreslených "teček" se figurka pohne na 
zvolené místo 

<img src="/media/gitlab/manual/chess_board_after_move_example.jpg" alt="UML" width="400"/>

Pokud se po tahu protivníkův král ocitne v šachu bude to vypadat následovně

<img src="/media/gitlab/manual/chess_board_check_example.jpg" alt="UML" width="400"/>

## Technická Dokumentace

TODO vygenerovaná dokumentace

### UML Diagram

<img src="/media/gitlab/uml.png" alt="UML" />
