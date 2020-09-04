import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TheTableComponent } from './the-table.component';

describe('TheTableComponent', () => {
  let component: TheTableComponent;
  let fixture: ComponentFixture<TheTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TheTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TheTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
