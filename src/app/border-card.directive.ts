import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[pkmnBorderCard]'
})
export class BorderCardDirective {
  constructor(private el: ElementRef) { 
    this.setHeight(230);
    this.setBorder('#f5f5f5');
  }

  @Input('pkmnBorderCard') borderColor: string;
  @Input() pkmnBorderCard: string;

  @HostListener('mouseenter') onMouseenter() {
    this.setBorder(this.borderColor || '#009688');
  }

  @HostListener('mouseleave') onMouseleave() {
    this.setBorder('#f5f5f5');
}

  private setHeight(height: number) {
    this.el.nativeElement.style.height = `${height}px`;
  }

  private setBorder(color: string) {
    let border = 'Solid 4px' + color;
    this.el.nativeElement.style.border = border;
  }
}
