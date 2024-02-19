import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[pkmnBorderCard]'
})
export class BorderCardDirective {
  constructor(private el: ElementRef) { 
    this.setHeight(this.defaultHeight);
    this.setBorder(this.initialColor);
  }

  private initialColor: string = '#f5f5f5';
  private defaultColor: string = '#009688';
  private defaultHeight: number = 180;


  @Input('pkmnBorderCard') borderColor: string;

  @HostListener('mouseenter') onMouseenter() {
    this.setBorder(this.borderColor || this.defaultColor);
  }

  @HostListener('mouseleave') onMouseleave() {
    this.setBorder(this.initialColor);
  }

  private setHeight(height: number) {
    this.defaultHeight;
  }

  private setBorder(color: string) {
    let border = 'Solid 4px' + color;
    this.el.nativeElement.style.border = border;
  }
}
