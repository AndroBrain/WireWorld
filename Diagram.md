## Diagrams

Mermaid Diagram:

```mermaid
classDiagram
ShowScreen ..> WireMapManager
WireMapManager ..> WorldDimensions
WireMapManager ..> Neighbourhood
WireMapManager ..> Rules
WireMapManager ..> Position
WireMapManager ..> WireBuilder
Output ..> Input
Output ..> WireMapManager
Neighbourhood <|-- Moor
Rules <|-- WireRules
WireBuilder ..> AndNotGateBuilder
WireBuilder ..> DiodeBuilder
WireBuilder ..> ElectronGeneratorBuilder
WireBuilder ..> OrGateBuilder
WireBuilder ..> FuseForN5Builder
FuseForN5Builder ..> AndNotGateBuilder
Input ..> WireBuilder
```

### Diode:

To w jakim kierunku ma zostać ona zbudowana jest wyznaczane z jej środka

### OrGate:

To w jakim kierunku ma zostać zbudowany jest wyznaczane ze środka

### ElectronGenerator:

To w jakim kierunku ma zostać zbudowany jest wyznaczane z przeciwnego rogu, czyli dla SW będzie to lewy górny róg, dla
NE będzie to prawy dolny róg