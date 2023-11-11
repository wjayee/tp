---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# AB-3 Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`AnimalLogic`**](#logic-component): The command executor.
* [**`AnimalModel`**](#model-component): Holds the data of the App in memory.
* [**`AnimalStorage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `AnimalLogic` component defines its API in the `AnimalLogic.java` interface and implements its functionality using the `AnimalLogicManager.java` class which follows the `AnimalLogic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts. Some core components include: `CommandBox`, `ResultDisplay`, `AnimalListPanel`, `AnimalDetailPanel`, and `AnimalCard`, along with other components. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder.

Generally, the UI consists of the command box for the user to input commands, a result display to give an output based on the command given, and a main animal display panel consisting of 2 parts:
1. `AnimalListPanel` which consists of a list of `AnimalCard` objects
2. `AnimalDetailPanel` which is a detailed view of `Animal` objects, that displays data about an animal only when selected in the `AnimalListPanel`.

The layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component does the following actions:

* executes user commands using the 'Logic' component, and this is done through the `CommandBox` class of the UI where users can input a command.
* listens for changes to `Model` data so that the UI can be updated with the modified data. For example, when an animal is deleted or added to the catalog, the UI is updated through the `AnimalListPanel` class, where the list that is referenced is an `Observable<Animal>` list that is updated live.
* listens for selecting of `AnimalCard` in the UI to display the selected `Animal` in the `AnimalDetailPanel` through a `ChangeListener` class in the `AnimalListPanel`. It will be initialized as `null`, hence displaying a blank screen. Only upon clicking an `AnimalCard` will the `ChangeListener` update the `AnimalDetailPanel` to reflect the selected animal.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Animal` object residing in the `Model`.

### Logic component

**API** : [`AnimalLogic.java`](https://github.com/AY2324S1-CS2103T-F08-3/tp/blob/master/src/main/java/seedu/address/logic/AnimalLogic.java)

Here's a (partial) class diagram of the `AnimalLogic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `AnimalLogic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `AnimalLogic` component works:

1. When `AnimalLogic` is called upon to execute a command, it is passed to an `AnimalCatalogParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `AnimalCommand` object (more precisely, an object of one of its subclasses e.g., `DeleteAnimalCommand`) which is executed by the `AnimalLogicManager`.
1. The command can communicate with the `AnimalModel` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `AnimalLogic`.

Here are the other classes in `AnimalLogic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AnimalCatalogParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddAnimalCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddAnimalCommand`) which the `AnimalCatalogParser` returns back as a `AnimalCommand` object.
* All `XYZCommandParser` classes (e.g., `AddAnimalCommandParser`, `DeleteAnimalCommandParser`, ...) inherit from the `AnimalParser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`AnimalModel.java`](https://github.com/AY2324S1-CS2103T-F08-3/tp/blob/master/src/main/java/seedu/address/model/AnimalModel.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `AnimalModel` component,

* stores the animal catalog data i.e., all `Animal` objects (which are contained in a `UniqueAnimalList` object).
* stores the currently 'selected' `Animal` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Animal>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `AnimalUserPref` object that represents the user’s preferences. This is exposed to the outside as a `AnimalReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `AnimalModel` represents data entities of the domain, they should make sense on their own without depending on other components)


<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `PetId` list in the `AnimalCatalog`, which `Animal` can reference. This allows `AnimalCatalog` to only require `PetId` object, instead of each `Animal` needing their own `PetId` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`AnimalStorage.java`](../src/main/java/seedu/address/storage/AnimalStorage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `AnimalStorage` component,
> Note: `AnimalStorage` and `Storage` are used synonymously.
* can save both animal catalog data and user preference data in JSON format, and read them back into corresponding
objects.

* inherits from both `AnimalCatalogStorage` and `AnimalUserPrefStorage`, which means it can be treated as either
one (if only the functionality of only one is needed). This also allows AnimalStorage to act as a unified facade that
exposes the relevant APIs for other classes to interact with the Storage.
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
that belong to the `Model`). Specifically, it depends on the `Animal` model. To decrease the amount of coupling,
`JsonAdaptedAnimal` is used, which acts as an adapter for `Animal` to be serialized/deserialized by Jackson. As such, it
is important for developers to take extra caution when modifying `Animal` and its attributes, and update
`JsonAdaptedAnimal` accordingly.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

## `AddAnimalCommand` Implementation

### Proposed Implementation

The `AddAnimalCommand` is a specific command designed to add an animal to the animal catalog. It identifies the animal to be added with attributes such as its name, ID, species, breed, age, date of birth and date of admission.

Here's a brief outline of its operations and attributes:

- `AddAnimalCommandParser#parse#` — Parses the user input to create an animal with the given attributes.
- `AddAnimalCommand#execute(AnimalModel model)` — Executes the command to add a specified animal to the model.

Given below is an example usage scenario of the `AddAnimalCommand`:

1. The user types in the `add` command with the proper cli syntax, giving a compulsory input field for each attribute. Note that the position of the cli syntax can be in any order.
2. The command verifies the validity of the index. If the index is missing any of the required syntax, it throws a `CommandException`. Otherwise, it adds the animal with its inputted attributes to the model and returns a successful command result.

The following sequence diagram shows how the `AddAnimalCommand` works:

<puml src="diagrams/AddSequenceDiagram.puml" alt="AddSequenceDiagram" />

### Design considerations:

**Aspect: How the addition is handled**

- **Alternative 1 (current choice):** Add the animal directly ALL of the attributes specified.
    - Pros: Straightforward for the user, since all attributes of the animals are handled at the start.
    - Cons: Requires many error handling in case of an invalid syntax, wrong format, or missing attributes.

- **Alternative 2:** Add the animal with mandatory attributes such as name and id but optional attributes for the rest.
    - Pros: Can be more intuitive if the user does not know all the information of the animal.
    - Cons: Have to create an optional field those attributes which require.  This can involve additional development work to allow users to input optional data as needed

_{more aspects and alternatives to be added}_

## `DeleteAnimalCommand` Implementation

### Proposed Implementation

The `DeleteAnimalCommand` is a specific command designed to remove an animal from the animal catalog. It identifies the animal to be deleted based on its displayed index in the list.

Here's a brief outline of its operations and attributes:

- `DeleteAnimalCommand#execute(AnimalModel model)` — Executes the command to delete a specified animal.
- `targetIndex` — An attribute that holds the index of the animal to be deleted.

Given below is an example usage scenario of the `DeleteAnimalCommand`:

1. The user views the list of animals in the animal catalog. Note that this can be a filtered list, such as after using the `find` command.
2. The user decides to delete a specific animal and executes the `delete` command, providing the index of the animal to be deleted. For instance, `delete 3` would aim to delete the third animal on the list.
3. The command verifies the validity of the index. If the index is out of bounds, it throws a `CommandException`. Otherwise, it retrieves the animal corresponding to the index, removes it from the model, and returns a successful command result.

The following sequence diagram shows how the `DeleteAnimalCommand` works:

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="DeleteSequenceDiagram" />

### Design considerations:

**Aspect: How the deletion is handled**

- **Alternative 1 (current choice):** Use an index to specify which animal to delete.
    - Pros: Straightforward for the user, especially if the list of animals is displayed.
    - Cons: Requires error handling in case of an invalid index.

- **Alternative 2:** Delete by specifying the animal's unique identifier or name.
    - Pros: Can be more intuitive if the user knows the specific animal's details.
    - Cons: Might lead to errors if multiple animals have similar names or if the user misspells the identifier.

_{more aspects and alternatives to be added}_

## `ListAnimalCommand` Implementation

### Proposed Implementation

The `ListAnimalCommand` is a command designed to list all animals in the animal catalog. It does not take in any arguments.

Here's a brief outline of its operations and attributes:
- `ListAnimalCommand#execute(AnimalModel model)` — Executes the command to list all animals in the animal catalog.

Given below is an example usage scenario of the `ListAnimalCommand`:
1. The user wants to view all animals that are in the catalog on the same page.
2. The user executes the `list` command.
3. The command retrieves all animals from the model and returns a successful command result.

The following sequence diagram shows how the `ListAnimalCommand` works:
<puml src="diagrams/ListSequenceDiagram.puml" alt="ListSequenceDiagram" />

### Design considerations:

Aspect: How the listing is handled
- Alternative 1 (current choice): Use a command to list all animals in the animal catalog.
    - Pros: Straightforward for the user.
    - Cons: None.
- Alternative 2: Display all animals in the animal catalog by default.
    - Pros: None.
    - Cons: Might be confusing for the user if the list is long.

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**: Volunteers at animal shelters.

**Value proposition**: Our application aims to provide a centralized platform to ensure efficient storage and access to critical details, enhancing the volunteer experience in animal shelters.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| **Priority** | **As a...**          | **I want to...**                          | **So that I can...**                  |
|--------------|----------------------|-------------------------------------------|---------------------------------------|
| `***`        | new volunteer        | view specific information about each pet. | understand their unique needs.        |
| `***`        | new volunteer        | be able to access a user manual.          | easily learn how to navigate the app. |
| `***`        | volunteer            | view all animals in a single page.        | -                                     |
| `***`        | authorized volunteer | add new animal entries                    | -                                     |
| `***`        | authorized volunteer | update existing animal profiles           | -                                     |


### Use cases

(For all use cases below, the **System** is the `AnimalCatalog` and the **Actor** is the `user`, unless specified
otherwise)

---

**Use Case: UC01 - Add Animal**

**MSS:**
1. User chooses to add an animal.
2. AnimalCatalog prompts for the animal details.
3. User provides the necessary animal details.
4. AnimalCatalog validates the details.
5. AnimalCatalog adds the pet to the shelter database and displays the success message.
   Use case ends.

**Extensions:**
3a. AnimalCatalog detects that the name is null.
3a1. AnimalCatalog displays the failure message: "Please input an animal name".
Use case resumes from step 2.

---

**Use Case: UC02 - List Animals**

**MSS:**
1. User chooses to list all animals.
2. AnimalCatalog retrieves all animals from the shelter database.
3. AnimalCatalog displays the list of all animals.
   Use case ends.

---

**Use Case: UC03 - Edit Animal**

**MSS:**
1. User chooses to edit an animal's details.
2. AnimalCatalog prompts for the index of the animal to be edited.
3. User provides the index and the updated details.
4. AnimalCatalog validates the details and the index.
5. AnimalCatalog updates the animal details and displays the success message.
   Use case ends.

**Extensions:**
3a. AnimalCatalog detects that the given index is out of range.
3a1. AnimalCatalog displays a failure message indicating an invalid index.
Use case resumes from step 2.

---

**Use Case: UC04 - Delete Animal**

**MSS:**
1. User chooses to delete an animal.
2. AnimalCatalog prompts for the index of the animal to be deleted.
3. User provides the index.
4. AnimalCatalog validates the index.
5. AnimalCatalog deletes the animal and displays the success message.
   Use case ends.

**Extensions:**
3a. AnimalCatalog detects that the given index is out of range.
3a1. AnimalCatalog displays a failure message indicating an invalid index.
Use case resumes from step 2.

*a. At any time, User chooses to cancel the deletion.
*a1. AnimalCatalog requests to confirm the cancellation.
*a2. User confirms the cancellation.
Use case ends.

---

### Non-Functional Requirements

1. The application should respond to user interactions (e.g., searches, edits) within 2 seconds.
2. The application should handle up to 200 contacts efficiently
3. The application should load GUI components and data within 5 seconds upon startup.
4. The application should adapt to various screen sizes and orientations to provide an optimal user experience on different devices
5. The application should be able to scale to accommodate an increasing number of users without significant changes to the system architecture.
6. The application user interface should be intuitive, requiring minimal training for new users.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Authorized volunteers**: Experienced volunteers who have been given a passcode to access limited features of the app.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

2. _{ more test cases …​ }_

## **Appendix: Planned Enhancements** ##
## Planned Enhancements

1. The current error message for invalid inputs for the `search` command is not very accurate. It will be improved in the future by providing more accurate error messages. e.g. `search nil/other` will return an error message saying that the prefix provided is invalid, instead of saying that the search input is empty since it is not recognised.

