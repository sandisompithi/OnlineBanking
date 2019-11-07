import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FixedAccountComponent } from './fixed-account.component';

describe('FixedAccountComponent', () => {
  let component: FixedAccountComponent;
  let fixture: ComponentFixture<FixedAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FixedAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FixedAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
