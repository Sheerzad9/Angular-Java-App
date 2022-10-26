import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'myCustomDatePipe',
})
export class CustomDatePipe implements PipeTransform {
  transform(value: any) {
    value = new Date(value).toLocaleString('fi-FI');
    let i = value.lastIndexOf('.');
    return value.substring(0, i);
  }
}
