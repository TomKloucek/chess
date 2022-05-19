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
- hra člověk vs. počítač
- kompletní kontrola pravidel hry
- možnost uložení a načtení rozehrané partie
- možnost manuálního umístění figur před zahájením hry
- šachové hodiny
- statistika hráčů
- síťová hra


## Uživatelská dokumentace
Naše aplikace je šachový klient, který umožňuje hrát šachy dle všech platných pravidel této hry. A to jak lokálně na jednom počítači ve formátu
hráč proti hráči nebo hráč proti umělé inteligenci, tak i online s pomocí serveru. Tento serve udržuje
jednotlivé roomky tzv. „místnosti“, ve které je připojena dvojice hráčů, která hraje proti sobě. Dále
naše aplikace také umožňuje vytvořit vlastní šachovou pozici a následně ji rozehrát opět buď proti
hráči nebo proti umělé inteligenci. Tuto šachovou pozici lze také uložit do souboru a později z tohoto
souboru načíst. Nyní se pojďme podívat na jednotlivé komponenty aplikace podrobněji.

Hlavní menu aplikace vypadá následovně:


<img src="/media/gitlab/manual/main_menu.png" alt="main_menu"/>

Zde můžeme vidět několik velké množství tlačítek, která jsou pojmenovaná podle funkcionality, za kterou odpovídají.

První tlačítko hra po sítí je zodpovědná za připojení se k serveru, zařezení do fronty a po nalezení hráče za otevřerní hry, 
která je hostovaná na serveru v tzv. „místnosti“. Před započetím hry, se klient uživatele zeptá na jeho herní jméno a to 
následujícím způsobem:

<img src="/media/gitlab/manual/name_question.png" alt="name_question"/>

Uživatel zadá své herní jméno po čemž se v případě dostupnosti serveru připojí na server, kde se mu otevře čekací dialog:

<img src="/media/gitlab/manual/waiting_dialogue.png" alt="name_question"/>

V opačném případě, pokud server není dostupný se mu otevře okno, které uživatele informuje o nedostupnosti serveru:

<img src="/media/gitlab/manual/server_not_available.png" alt="server_not_availible"/>

V případě připojení na server druhého hráče, se otevře místnost pro šachovou hru, která vypadá následovně

Naše šachovnice bude vypadat následujicím způsobem:

<img src="/media/gitlab/manual/game_window.png" alt="game_window" />

V něm můžeme spatřit několik elementů takových jako uživatelská jména hráčů, šachové hodiny (které jsou v online hře nastaveny na výchozích 10 minut času pro každého hráče), okno s notací a samozřejmě šachovnici.

Po zvolení figurky se na šachovnici zobrazí možné tahy vybrané figurky, odpovídající pravidlům šachu:

<img src="/media/gitlab/manual/move_example.png" alt="game_window" />

Následovně hra probíhá dle klasického scénáře dokud nenastane remíza, nebo jeden z hráču neprohraje.

Po ukončení hry se uživateli zobrazí hláška, kdo vyhrál a bude odpojen od serveru, čímž se mu také zavře okno s 
šachovou hrou a otevře hlavní menu.

<img src="/media/gitlab/manual/end_of_game.png" alt="end_of_game" />

Dále se podíváme na tlačítko Hra s počítačem. Toto tlačítko umožňuje spustit hru s počítačem. S předem navoleným časovým limitem pro každého hráče.
Toto nastavení se dá měnit uvnitř šachového klienta po kliknutí na tlačítko nastavení a následně na tlačítko změnit délku hry.

Hra v tomto módu probíhá stejně jako v síťové hře, akorát, že proti hráči hraje umělá inteligence. Hra končí za stejných podmínek jako 
v přechozím herním režimu.

Další režim hry je PvP. Jedná se o klasickou implementaci lokální hry v jednom šachovém klientu, kdy proti sobě hrají dva hráči na jednom počítači.
Ukončovací podmínky jsou opět stejné.

Tlačítko Statistiky odpovídá za načtení statistik jednolivého hráče z herního serveru nebo za načtení veškerých her, které byly na serveru odehrány. 
Pro výběr toho co si uživatel chce zobrazit je vykresleno speciální okno:

<img src="/media/gitlab/manual/statistics_dialogue.png" alt="statistics_dialogue" />

Pokud uživatel zvolí první možnost čili osobní statistika, bude vyzván k zadání jména hráče, kterého si chce zobrazit:

<img src="/media/gitlab/manual/name_question.png" alt="name_question" />

Poté se uživateli zobrazí okno se statistikou

<img src="/media/gitlab/manual/statistics_window.png" alt="statistics_window" />

Když uživatel klikne na tlačítko: Všechny hry, tak se mu zobrazí okno s nabídkou her, které si může prohlédnout:

<img src="/media/gitlab/manual/game_history.png" alt="game_history" />

Poté uživatel může kliknout na jednotlivou hru a tam se mu zobrazí toto okno se dvěmi tlačítky, kterými může průběh hry posouvat 
a zobrazovat si tak tahy, které proběhly ve hře:

<img src="/media/gitlab/manual/game_browser.png" alt="game_browser" />


Tlačítko Editor, otevírá rozhraní, ve kterém si uživatel může vytvořit vlastní šachovou pozici a následně ji rozehrát proti počítači, 
proti reálnému hráči lokálně, nebo si tuto pozici může uložit toto rozhraní vypadá následovně: 

<img src="/media/gitlab/manual/editor.png" alt="editor" />

Na pravé straně můžeme vidět tlačítka odpovídající za výše popsanou funkcionalitu. 

Pokud uživatel chce umístit figurku na šachovnici, musí kliknout na políčko kam figurku chce umístit na což se mu zobrazí
dva dialogy, jeden pro zvolení barvy figurky:

<img src="/media/gitlab/manual/choose_color_of_piece.png" alt="choose_color_of_piece" />


a druhý pro zvolení požadované figurky:

<img src="/media/gitlab/manual/choose_piece.png" alt="choose_piece" />

Dalším tlačítkem je tlačítko, které slouží pro načtení pozice, ze souboru, do kterého uživatel uložil hru, viz výše popsané možnosti.

Po kliknutí na toto tlačítko stejně tak jako po kliknutí na tlačítko exportuj šachovnici pro pozdější načtení se zobrazí tento dialog:

<img src="/media/gitlab/manual/file_browser.png" alt="file_browser" />

Ve kterém si uživatel vybere soubor, ze kterého se mu načtě pozice.

<img src="/media/gitlab/manual/editor_preview.png" alt="editor_preview" />

Posledním tlačítkem je Nastavení. Název je vypovídající, po kliknutí na toto tlačítko se zobrazí menu, ve kterém si uživatel
zvolí co chce nastavit 

<img src="/media/gitlab/manual/settings.png" alt="settings" />

Uživatel si může nastavit barvu políček jak bílé tak černé barvy, 

<img src="/media/gitlab/manual/color_settings.png" alt="color_settings" />

délku trvání her, které se budou odehrávat lokálně 

<img src="/media/gitlab/manual/time_settings.png" alt="time_settings" />

a nakonec vzhled šachových figurek. 

<img src="/media/gitlab/manual/pieces_setting.png" alt="time_settings" />

Pro uložení nastavení je potřeba zvolit požadované nastavení a pak okno s nastavením jen zavřít.
Následně se pak všechny provedené změny zobrazí v šachovém klientu.



## Technická Dokumentace

Technická dokumentace je vygenerována v JavaDoc a nachází se v repositáři ve složce javadoc.

### UML Diagram

V současné době neobsahuje všechny třídy, v budoucnu přibydou třídy, které budou mít podobné názvy těmto:

chessTimer s metodami, které budou umožňovat sledování času a přepínání hodin, při změně tahu

historyManagement s metodami, které budou umožňovat zobrazit odehranou hru uživatele

userManagement s metodami, které budou umožňovat založení účtu a hru pod daným účtem

chessServer s metodami, které budou umožňovat síťovou hru



<img src="/media/gitlab/uml.png" alt="UML" />
