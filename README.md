# Kawauso

Kawauso is a test-driven application that demonstrates using [FireOtter](https://github.com/Versal/fireotter) for spreadsheet-based test specification.

Tests in Kawauso are derived from human-readable specifications living in a spreadsheet under *src/test/resources/arithmetic.csv*.  Application features are built in response to new (failing) tests added as specifications to the spreadsheet.

## Example

Consider the introduction of a new feature to Kawauso, with which users can carry a base integer to an arbitrary integer power.

The first step in creating this feature is to determine the success criteria.  Let's start simple:

* Feature: Exponentiation
* Function name: `pow`
* Sample input: *42, 2*
* Expected output: *1764*

Now we can add a row to the spreadsheet:

<table>
  <tr><th>test</th><th>function</th><th>input 1</th><th>input 2</th><th>expected output</th></tr>
  <tr><td>Exponentiation</td><td>pow</td><td>42</td><td>2</td><td>1764</td></tr>
</table>

This causes the tests to break, because the test code doesn't know what to do with the `pow` function:

```
[error] Could not run test com.versal.kawauso.tests.ArithmeticTest: scala.MatchError: pow (of class java.lang.String)
```

Let's fix this in *tests.scala*:

```scala
val output = spec(1) match {
  case "add"      => Arithmetic.add(input1, input2)
  case "subtract" => Arithmetic.subtract(input1, input2)
  case "multiply" => Arithmetic.multiply(input1, input2)
  case "divide"   => Arithmetic.divide(input1, input2)
  case "pow"      => Arithmetic.pow(input1, input2)
}
```

The test now fails because there is no `Arithmetic#pow` function:

```
[error] src/test/scala/tests.scala:20: value pow is not a member of object com.versal.kawauso.Arithmetic
[error]       case "pow"      => Arithmetic.pow(input1, input2)
```

Let's implement `Arithmetic#pow` in *kawauso.scala*:

```scala
object Arithmetic {
  def add(x: Int, y: Int): Int = x + y
  def subtract(x: Int, y: Int): Int = x - y
  def multiply(x: Int, y: Int): Int = x * y
  def divide(x: Int, y: Int): Int = x / y
  def pow(x: Int, y: Int): Int = math.pow(x,y).toInt
}
```

The new test now passes:

```
[info] ArithmeticTest:
[info] - Addition
[info] - Subtraction
[info] - Multiplication
[info] - Division
[info] - Exponentiation
[info] Passed: : Total 5, Failed 0, Errors 0, Passed 5, Skipped 0
```

