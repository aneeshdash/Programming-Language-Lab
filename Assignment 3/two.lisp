(defun square (x) (* x x 4))
(defun thi (x) (- (* x x x x 4) (* x x x 4)))
(defun sec (x) (* 4 (exp x)))

(defun simpsons (f a b n)
  (setq h  (/ (- b a) n))
  (defun yk (k)
	(funcall f (+ a (* k h)))
  )
  (setq s (+ (yk 0) (yk n))) 
  (loop for i from 1 to (- n 1)
  	if(oddp i)
   	do(setq s (+ s (* 4 (yk i))))
  )
  (loop for i from 2 to (- n 2)
  	if(evenp i)
   	do(setq s (+ s (* 2 (yk i))))
  )
  (/ (* h s) 3.0)

)

(print (min (simpsons 'square 2 4 100) (simpsons 'thi 2 4 100) (simpsons 'sec 2 4 100) ))

