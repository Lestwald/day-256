# day-256

## Run
```
  gradle startService
```
## Examples:
```
  request: http://localhost?year=1997
  response: {"errorCode":200,"dataMessage":"13/09/97"}
```
```
  request: http://localhost?year=2020
  response: {"errorCode":200,"dataMessage":"12/09/20"}
```
```
  request: http://localhost?year=-1
  response: {"errorCode":1,"dataMessage":"Wrong year"}
 ```
